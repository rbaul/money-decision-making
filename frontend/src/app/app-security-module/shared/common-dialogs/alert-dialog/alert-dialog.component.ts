import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-alert-dialog',
  templateUrl: './alert-dialog.component.html',
  styleUrls: ['./alert-dialog.component.scss']
})
export class AlertDialogComponent implements OnInit {

  data: AlertDialogData = {};

  constructor(
    private dialogRef: MatDialogRef<AlertDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data: AlertDialogData) {
    this.data.messages = (data && data.messages) ? data.messages : ['Unexpected Error'];
    this.data.buttonName = (data && data.buttonName) ? data.buttonName : 'OK';
    this.data.title = (data && data.title) ? data.title : 'Error';
  }

  ngOnInit() {
  }

}

export interface AlertDialogData {
  messages?: string[];
  buttonName?: string;
  title?: string;
}
