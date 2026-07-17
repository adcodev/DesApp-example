import { Component, inject, signal } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { UserService } from '../../../core/services/user.service';
import { PageLayout } from '../../../shared/components/page-layout/page-layout';
import { UserForm } from '../../../shared/components/user-form/user-form';

@Component({
  selector: 'app-register',
  imports: [RouterLink, PageLayout, UserForm],
  templateUrl: './register.html',
})
export class Register {
  private fb = inject(FormBuilder);
  private userService = inject(UserService);
  private router = inject(Router);

  loading = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  registerForm: FormGroup = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    role: ['', Validators.required],
  });

  onSubmit(): void {
    if (this.registerForm.invalid) return;

    this.loading.set(true);
    this.error.set(null);
    this.success.set(false);

    this.userService.registerUser(this.registerForm.value).subscribe({
      next: (response) => {
        this.loading.set(false);
        this.success.set(true);
        this.registerForm.reset();
        setTimeout(() => this.router.navigate(['/users']), 1500);
      },
      error: () => {
        this.error.set('No se pudo registrar el usuario. Verifica los datos e intenta de nuevo.');
        this.loading.set(false);
      },
    });
  }
}
