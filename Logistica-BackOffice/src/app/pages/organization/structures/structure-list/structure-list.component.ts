import {AfterViewInit, Component, NgZone, OnDestroy, OnInit, Renderer2, ViewEncapsulation} from '@angular/core';
import {BreadCrumb} from '../../../../core/models/all.models';
import {process, State} from '@progress/kendo-data-query';
import {FormBuilder} from '@angular/forms';
import {StructuresService} from '../../../../core/services/Organzation/structures.service';
import {fromEvent, Subscription} from 'rxjs';
import {take, tap} from 'rxjs/operators';
import {RowClassArgs} from '@progress/kendo-angular-grid';

const tableRow = (node) => node.tagName.toLowerCase() === 'tr';

const closest = (node, predicate) => {
  while (node && !predicate(node)) {
    node = node.parentNode;
  }
  return node;
};

@Component({
  selector: 'app-structure-list',
  templateUrl: './structure-list.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./structure-list.component.scss'],
})
export class StructureListComponent implements OnInit, AfterViewInit, OnDestroy {
  breadCrumb: BreadCrumb;
  gridData;
  public gridState: State = {
    sort: [],
    skip: 0,
    take: 10
  };
  private currentSubscription: Subscription;

  constructor(
    public formBuilder: FormBuilder,
    private service: StructuresService, private renderer: Renderer2, private zone: NgZone) {
  }


  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Structures',
      items: [
        {label: 'Structures', active: true}
      ]
    };
    // todo: use simple tables
    this.service.getAll().subscribe(data => this.gridData = process(data, this.gridState));
  }

  public ngAfterViewInit(): void {
    this.currentSubscription = this.handleDragAndDrop();
  }

  public ngOnDestroy(): void {
    this.currentSubscription.unsubscribe();
  }

  public dataStateChange(state: State): void {
    this.gridState = state;
    this.service.getAll().subscribe(data => this.gridData = process(data, this.gridState));
    this.currentSubscription.unsubscribe();
    this.zone.onStable
      .pipe(take(1))
      .subscribe(() => (this.currentSubscription = this.handleDragAndDrop()));
  }

  public rowCallback(context: RowClassArgs) {
    return {
      dragging: context.dataItem.dragging,
    };
  }


  private handleDragAndDrop(): Subscription {
    const sub = new Subscription(() => {
    });
    let draggedItemIndex;

    const tableRows = Array.from(document.querySelectorAll('.k-grid tr'));
    tableRows.forEach((item) => {
      this.renderer.setAttribute(item, 'draggable', 'true');
      const dragStart = fromEvent<DragEvent>(item, 'dragstart');
      const dragOver = fromEvent(item, 'dragover');
      const dragEnd = fromEvent(item, 'dragend');

      sub.add(
        dragStart
          .pipe(
            tap(({dataTransfer}) => {
              try {
                const dragImgEl = document.createElement('span');
                dragImgEl.setAttribute(
                  'style',
                  'position: absolute; display: block; top: 0; left: 0; width: 0; height: 0;'
                );
                document.body.appendChild(dragImgEl);
                dataTransfer.setDragImage(dragImgEl, 0, 0);
              } catch (err) {
                // IE doesn't support setDragImage
              }
              try {
                // Firefox won't drag without setting data
                dataTransfer.setData('application/json', '');
              } catch (err) {
                // IE doesn't support MIME types in setData
              }
            })
          )
          .subscribe(({target}) => {
            const row: HTMLTableRowElement = target as HTMLTableRowElement;
            draggedItemIndex = row.rowIndex;
            const dataItem = this.gridData.data[draggedItemIndex];
            dataItem.dragging = true;
          })
      );

      sub.add(
        dragOver.subscribe((e: any) => {
          e.preventDefault();
          const dataItem = this.gridData.data.splice(draggedItemIndex, 1)[0];
          const dropIndex = closest(e.target, tableRow).rowIndex;
          const dropItem = this.gridData.data[dropIndex];

          draggedItemIndex = dropIndex;
          this.zone.run(() =>
            this.gridData.data.splice(dropIndex, 0, dataItem)
          );
        })
      );

      sub.add(
        dragEnd.subscribe((e: any) => {
          e.preventDefault();
          const dataItem = this.gridData.data[draggedItemIndex];
          dataItem.dragging = false;
        })
      );
    });

    return sub;
  }
}
