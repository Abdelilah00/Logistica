import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../../core/models/all.models';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Location} from '@angular/common';
import {finalize} from 'rxjs/operators';
import {StructuresService} from '../../../../core/services/Organzation/structures.service';
import {StructureUnitsService} from '../../../../core/services/Organzation/structure-units.service';
import {Structure} from '../../../../core/models/Organization.model';

@Component({
  selector: 'app-unite-create',
  templateUrl: './unite-create.component.html',
  styleUrls: ['./unite-create.component.scss']
})
export class UniteCreateComponent implements OnInit {

  formGroup = this.createFormGroup();
  saving = false;
  public breadCrumb: BreadCrumb;
  structureList: Structure[];

  constructor(private formBuilder: FormBuilder,
              private service: StructureUnitsService,
              private matSnackBar: MatSnackBar,
              private location: Location,
              private structuresService: StructuresService
  ) {
  }

  get form() {
    return this.formGroup.controls;
  }

  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Add Unite',
      items: [
        {label: 'Units', path: '../'},
        {label: 'Create', active: true}
      ]
    };
    this.structuresService.getAll().subscribe(data => this.structureList = data);
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      name: ['service lb3abé3', Validators.required],
      structureId: [3, Validators.required],
    });
  }

  submit(): void {
    this.saving = true;
    this.service.create(this.formGroup.value)
      .pipe(
        finalize(() => {
          this.saving = false;
        })
      ).subscribe(() => {
      // Show the success message
      this.matSnackBar.open('Unite saved', 'Ok', {
        verticalPosition: 'top',
        duration: 3000,
        panelClass: ['green-snackbar']
      });
      this.goBack();
    });
  }

  goBack(): void {
    this.location.back();
  }
}
