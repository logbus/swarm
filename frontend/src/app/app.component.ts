import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

interface User {
  lastName: string;
  firstName: string;
  client: string;
  caller: string;
  backend: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  public requestTime: String;
  public backendResponse: User;
  public backendRequestTime: number;

  public constructor(private http: HttpClient) {
  }

  public ngOnInit(): void {
    this.requestTime = new Date().toLocaleTimeString("de");
    this.backendRequestTime = 0;
    // Call Backend
    const startRequest = new Date().getMilliseconds();
    this.http.get<User>("/api/users").subscribe(user => {
      this.backendResponse = user;
      const endRequestTime = new Date().getMilliseconds();
      this.backendRequestTime =  endRequestTime - startRequest;
    });
  }

  public reload() {
    window.location.reload();
  }
}
