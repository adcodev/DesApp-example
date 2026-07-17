import { Component, input, output } from '@angular/core';
import { ReactiveFormsModule, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-user-form',
  imports: [ReactiveFormsModule],
  templateUrl: './user-form.html',
})
export class UserForm {
  form = input.required<FormGroup>();
  submitLabel = input<string>('Guardar');
  loading = input<boolean>(false);

  submitted = output<void>();
}
