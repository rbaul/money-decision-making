import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.component.html',
  styleUrls: ['./confirmation-dialog.component.scss']
})
export class ConfirmationDialogComponent implements OnInit {

  data: ConfirmationDialogData = {};

  constructor(
    private dialogRef: MatDialogRef<ConfirmationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data: ConfirmationDialogData) {
    this.data.message = (data && data.message) ? data.message : 'Are you sure?';
    this.data.okName = (data && data.okName) ? data.okName : 'OK';
    this.data.title = (data && data.title) ? data.title : 'Confirmation';
  }

  ngOnInit() {
  }

}

export interface ConfirmationDialogData {
  message?: string;
  okName?: string;
  title?: string;
}
