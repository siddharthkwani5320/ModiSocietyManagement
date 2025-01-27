import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  username = "siddharth"
  picture= "https://lh3.googleusercontent.com/a/ACg8ocLHwjAefwhf3P2dzkaH4VETxAiXEmywU8gMItdzNYWvHhaB990Q=s96-c"
  constructor(private http:HttpClient) { }

  ngOnInit(): void {

  }
}
