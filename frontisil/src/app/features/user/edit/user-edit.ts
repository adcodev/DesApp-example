import { Component, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { UserService } from '../../../core/services/user.service';
import { PageLayout } from '../../../shared/components/page-layout/page-layout';
import { UserForm } from '../../../shared/components/user-form/user-form';

@Component({
  selector: 'app-user-edit',
  imports: [RouterLink, PageLayout, UserForm],
  templateUrl: './user-edit.html',
})
export class UserEdit implements OnInit {
  private fb = inject(FormBuilder);
  private userService = inject(UserService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  editForm!: FormGroup;
  loading = signal(false);
  pageLoading = signal(true);
  error = signal<string | null>(null);
  success = signal(false);
  userId = signal<string | null>(null);

  ngOnInit(): void {
    const id = String(this.route.snapshot.paramMap.get('id'));
    this.userId.set(id);

    this.editForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      role: ['', Validators.required],
    });

    this.userService.getUserById(id).subscribe({
      next: (user) => {
        this.editForm.patchValue(user);
        this.pageLoading.set(false);
      },
      error: () => {
        this.error.set('No se pudo cargar el usuario.');
        this.pageLoading.set(false);
      },
    });
  }

  onSubmit(): void {
    if (this.editForm.invalid || this.userId() === null) return;

    this.loading.set(true);
    this.error.set(null);
    this.success.set(false);

    this.userService.updateUser(this.userId()!, this.editForm.value).subscribe({
      next: () => {
        this.loading.set(false);
        this.success.set(true);
        setTimeout(() => this.router.navigate(['/users']), 1500);
      },
      error: () => {
        this.error.set('No se pudo actualizar el usuario.');
        this.loading.set(false);
      },
    });
  }
}
