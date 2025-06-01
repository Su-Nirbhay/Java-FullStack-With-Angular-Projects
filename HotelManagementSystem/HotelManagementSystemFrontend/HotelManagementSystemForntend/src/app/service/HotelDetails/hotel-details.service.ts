import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HotelDetailsService {

  constructor(private http: HttpClient) { }

  price!:any;
  getUrlACSingleBed = "http://localhost:9090/hotel/getprice/AC Single Bed";
  getUrlACDoubleBed = "http://localhost:9090/hotel/getprice/AC Double Bed";
  getUrlNONACSingleBed = "http://localhost:9090/hotel/getprice/NON-AC Single Bed";
  getUrlNONACDoubleBed = "http://localhost:9090/hotel/getprice/NON-AC Double Bed";

  getPriceACSingleBed():Observable<any>{
    return this.price=this.http.get<number>(this.getUrlACSingleBed);
  }
  getPriceACDoubleBed():Observable<any>{
    return this.price=this.http.get<number>(this.getUrlACDoubleBed);
  }
  getPriceNONACSingleBed():Observable<any>{
    return this.price=this.http.get<number>(this.getUrlNONACSingleBed);
  }
  getPriceNONACDoubleBed():Observable<any>{
    return this.price=this.http.get<number>(this.getUrlNONACDoubleBed);
  }

}
