import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

interface User {
  lastName: string;
  firstName: string;
  io: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  public requestTime: String;
  public backendResponse: String;
  public backendRequestTime: number;

  public constructor(private http: HttpClient) {
  }

  public ngOnInit(): void {
    this.requestTime = new Date().toLocaleTimeString("de");
    this.backendRequestTime = 0;
    // Call Backend
    this.backendResponse = "waiting for response...";
    const startRequest = new Date().getMilliseconds();
    this.http.get<User>("/api/users").subscribe(data => {
      this.backendResponse = JSON.stringify(data);
      this.backendRequestTime = new Date().getMilliseconds() - startRequest;
    });
  }

  public reload() {
    window.location.reload();
  }
}
