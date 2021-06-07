import {Component, Input, OnInit} from '@angular/core';
import {InputDetail, OutputDetail} from '../../../../core/models/all.models';
import {FormBuilder, FormGroup} from '@angular/forms';
import {OutputDetailsService} from '../../../../core/services/Products/output-details.service';


@Component({
  selector: 'app-output-details',
  templateUrl: './output-details.component.html',
  styleUrls: ['./output-details.component.scss']
})

export class OutputDetailsComponent implements OnInit {
  view: OutputDetail[];
  @Input() inputId: number | null;
  public editDataItem: OutputDetail;
  public isNew: boolean;

  submitted: boolean;

  // customersData: TransactionDetail[];
  validationForm: FormGroup;

  constructor(private outputDetailsService: OutputDetailsService,
              public formBuilder: FormBuilder) {
  }

  get form() {
    return this.validationForm.controls;
  }


  ngOnInit(): void {
    console.log(this.inputId);
    if (this.inputId !== null) {
      this.outputDetailsService.getByOutputId(this.inputId).subscribe(data => this.view = data);
    }
  }

  public addHandler(): void {
    const tmp = new OutputDetail();
    tmp.outputId = this.inputId;
    this.editDataItem = tmp;
    this.isNew = true;
  }
}
