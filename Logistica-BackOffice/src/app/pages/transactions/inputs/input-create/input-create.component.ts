import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../../core/models/auth.models';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {InputService} from '../../../../core/services/input.service';

@Component({
  selector: 'app-input-create',
  templateUrl: './input-create.component.html',
  styleUrls: ['./input-create.component.scss']
})

export class InputCreateComponent implements OnInit {

  public breadCrumb: BreadCrumb;
  selectValue = ['Touchscreen', 'Call Function', 'Notifications', 'Fitness', 'Outdoor'];
  formGroup = this.createFormGroup();

  constructor(private service: InputService,
              private formBuilder: FormBuilder,
              /*private location: Location,
              private matSnackBar: MatSnackBar*/) {
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      supplierName: ['test', Validators.required],
      date: ['', Validators.required],
      description: ['test', Validators.required],

      transactionDetails: this.formBuilder.array([]),
    });
  }

  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Add Input',
      items: [
        {label: 'Inputs', path: '../'},
        {label: 'Add Input', active: true}
      ]
    };
  }
}
