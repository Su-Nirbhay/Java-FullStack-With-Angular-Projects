import { Routes } from '@angular/router';
import { UserComponent } from './component/user/user.component';
import { HomePageComponent } from './component/home-page/home-page.component';
import { ReservationComponent } from './component/reservation/reservation.component';

export const routes: Routes = [
    { path: '', component: HomePageComponent } ,
    { path: 'user' , component: UserComponent} , 
    { path: 'reservation' , component : ReservationComponent}
    
];
