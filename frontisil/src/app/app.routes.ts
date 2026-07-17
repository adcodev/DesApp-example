import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'users',
    pathMatch: 'full',
  },
  {
    path: 'users',
    loadComponent: () => import('./features/user/list/user-list').then((m) => m.UserList),
  },
  {
    path: 'users/reports',
    loadComponent: () => import('./features/user/report/user-report').then((m) => m.UserReport),
  },
  {
    path: 'users/:id/edit',
    loadComponent: () => import('./features/user/edit/user-edit').then((m) => m.UserEdit),
  },
  {
    path: 'register',
    loadComponent: () => import('./features/user/register/register').then((m) => m.Register),
  },
  {
    path: '**',
    redirectTo: 'users',
  },
];
