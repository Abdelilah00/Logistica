import {Component, OnInit} from '@angular/core';


import {LAYOUT_VERTICAL} from './layouts.model';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

  // layout related config
  layoutType: string;

  constructor() {
  }

  ngOnInit() {
    // default settings
    this.layoutType = LAYOUT_VERTICAL;
    // listen to event and change the layout, theme, etc
  }

  /**
   * Check if the vertical layout is requested
   */
  isVerticalLayoutRequested() {
    return this.layoutType === LAYOUT_VERTICAL;
  }

}
