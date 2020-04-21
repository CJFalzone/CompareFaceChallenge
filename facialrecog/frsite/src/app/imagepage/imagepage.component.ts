import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatHorizontalStepper } from '@angular/material/stepper';
import {ProgressSpinnerMode} from '@angular/material/progress-spinner';
import { ThemePalette } from '@angular/material/core';

@Component({
  selector: 'app-imagepage',
  templateUrl: './imagepage.component.html',
  styleUrls: ['./imagepage.component.css']
})
export class ImagepageComponent implements OnInit {

  public selectedFile1;
  public selectedFile2;
  public selectedFile3;
  receivedImageData: any;
  base64Data: any;
  convertedImage: any;
  // URL = 'http://localhost:9883/upload/addimages';
  URL = 'http://ec2-18-191-17-153.us-east-2.compute.amazonaws.com:9883/upload/addimages'; // from EC2
  results: any;

  @ViewChild(MatHorizontalStepper) stepper: MatHorizontalStepper;
  step1Completed = false;
  step2Completed = false;
  step3Completed = false;

  color: ThemePalette = 'primary';
  mode: ProgressSpinnerMode = 'indeterminate';
  value = 50;
  showSpinner = false;

  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {

  }
  public  onFileChanged1(event) {
    console.log(event);
    this.selectedFile1 = event.target.files[0];
    console.log(this.selectedFile1);
    this.step1Completed = true;
  }
  public  onFileChanged2(event) {
    console.log(event);
    this.selectedFile2 = event.target.files[0];
    console.log(this.selectedFile2);
    this.step2Completed = true;
  }
  public  onFileChanged3(event) {
    console.log(event);
    this.selectedFile3 = event.target.files[0];
    console.log(this.selectedFile3);
    this.step3Completed = true;
  }

  upload() {

    const uploadData = new FormData();
    uploadData.append('imageFile1', this.selectedFile1, this.selectedFile1.name);
    uploadData.append('imageFile2', this.selectedFile2, this.selectedFile2.name);
    uploadData.append('imageFile3', this.selectedFile3, this.selectedFile3.name);

    this.showSpinner = true;

    this.httpClient.post(this.URL, uploadData).subscribe(
      res => {console.log('images sent');
              console.log(uploadData);
              this.results = res;
              console.log(this.results);
              this.showSpinner = false;
              },
               err => console.log('Error Occured during saving: ' + err)
            );
  }

}
