import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserSettingsService } from 'src/app/services/user-settings.service';
//import { UserSettings } from 'src/app/interface/user-settings';

@Component({
  selector: 'app-settings',
  templateUrl: './setting.component.html',
  styleUrls: ['./setting.component.css']
})
export class SettingComponent implements OnInit {
  //userSettings!: UserSettings;
 // updatedSettings!: UserSettings;
 formUser:any=FormGroup;

  constructor(private userSettingsService: UserSettingsService,
              private formBuilder:FormBuilder,
              private router:Router) { }

  ngOnInit(): void {
   // this.getUserSettings(1); // Change the ID as per your requirement
   this.dataForm();
  }

  /*getUserSettings(id: number): void {
    this.userSettingsService.getUserSettings(id)
      .subscribe((settings: UserSettings) => {
        this.userSettings = settings;
        this.updatedSettings = { ...settings };
      });
  }*/

  saveSettings2(): void {
    /*this.userSettingsService.updateUserSettings(this.userSettings.id, this.updatedSettings)
      .subscribe((updatedSettings: UserSettings) => {
        this.userSettings = updatedSettings;
      });*/
  }
  dataForm(){
    this.formUser = this.formBuilder.group({
      oldEmail:[null,Validators.required],
      userName:[null,Validators.required],
      password:[null,Validators.required],
    })
  }
  saveSettings(){
    var form = this.formUser.value;
   var oldEmail=form.oldEmail;
   console.log(oldEmail);
    var data= {
      userName:form.userName,
      password:form.password
    }
    this.userSettingsService.updateUserSettings(data,oldEmail).subscribe(
      dara=>{
        console.log(data),
        this.router.navigateByUrl("/dashboard");
      },
      error=>{
        console.log(error);
      }
    )
  }
}