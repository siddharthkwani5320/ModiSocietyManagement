import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  username = "siddharth"
  constructor(private http:HttpClient) { }

  ngOnInit(): void {
    this.http.get("localhost:8080/api/user").subscribe(response=>{
      console.log(response)
    })
  }

}
