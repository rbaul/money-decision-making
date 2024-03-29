import {Component, Inject, OnInit} from '@angular/core';
import {PrivilegeResponseDto, RoleRequestDto, RoleResponseDto} from 'src/app/app-security-module/models/role';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {PrivilegeApiService} from 'src/app/app-security-module/services/privilege-api.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-role-dialog',
  templateUrl: './role-dialog.component.html',
  styleUrls: ['./role-dialog.component.scss']
})
export class RoleDialogComponent implements OnInit {

  form: FormGroup;

  privileges: PrivilegeResponseDto[] = [];

  constructor(
    private fb: FormBuilder,
    private privilegeApiService: PrivilegeApiService,
    private dialogRef: MatDialogRef<RoleDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data: RoleResponseDto) {
    this.privilegeApiService.getPrivileges().subscribe(privilegesResponse => {
      this.privileges = privilegesResponse;
      const selectedPrivileges = data.privileges ?
        this.privileges.filter(privilege => data.privileges && data.privileges.map(r => r.id).indexOf(privilege.id) > -1) : [];

      this.form.patchValue({
        privileges: selectedPrivileges
      });
    });

    this.form = this.fb.group({
      name: [data.name, [Validators.required]],
      description: [data.description, [Validators.required]],
      privileges: [[], [Validators.required, Validators.min(1)]]
    });
  }

  ngOnInit() {
  }

  save() {
    const roleRequest: RoleRequestDto = {
      name: this.form.value.name,
      description: this.form.value.description,
      privilegeIds: this.form.value.privileges.map((privilege: PrivilegeResponseDto) => privilege.id),
    };
    this.dialogRef.close(roleRequest);
  }

}
