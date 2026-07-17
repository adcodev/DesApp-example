import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { UserService } from '../../../core/services/user.service';
import { UserResponse } from '../../../core/models/user.model';
import { PageLayout } from '../../../shared/components/page-layout/page-layout';

@Component({
  selector: 'app-user-list',
  imports: [RouterLink, PageLayout],
  templateUrl: './user-list.html',
})
export class UserList implements OnInit {
  private userService = inject(UserService);

  users = signal<UserResponse[]>([]);
  loading = signal(true);
  error = signal<string | null>(null);
  searchQuery = signal('');
  deletingId = signal<string | null>(null);
  confirmDeleteId = signal<string | null>(null);

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.loading.set(true);
    this.error.set(null);

    this.userService.getUsers().subscribe({
      next: (users) => {
        this.users.set(users);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('No se pudieron cargar los usuarios. Verifica que el servidor esté activo.');
        this.loading.set(false);
      },
    });
  }

  filteredUsers(): UserResponse[] {
    const query = this.searchQuery().toLowerCase().trim();
    if (!query) return this.users();

    return this.users().filter(
      (user) =>
        user.name.toLowerCase().includes(query) ||
        user.email.toLowerCase().includes(query) ||
        user.role.toLowerCase().includes(query),
    );
  }

  onSearch(event: Event): void {
    const input = event.target as HTMLInputElement;
    this.searchQuery.set(input.value);
  }

  askDelete(id: string): void {
    this.confirmDeleteId.set(id);
  }

  cancelDelete(): void {
    this.confirmDeleteId.set(null);
  }

  confirmDelete(): void {
    const id = this.confirmDeleteId();
    if (id === null) return;

    this.deletingId.set(id);
    this.userService.deleteUser(id).subscribe({
      next: () => {
        this.users.update((list) => list.filter((u) => u.id !== id));
        this.confirmDeleteId.set(null);
        this.deletingId.set(null);
      },
      error: () => {
        this.error.set('No se pudo eliminar el usuario.');
        this.deletingId.set(null);
        this.confirmDeleteId.set(null);
      },
    });
  }

  getRoleBadgeClass(role: string): string {
    const classes: Record<string, string> = {
      100: 'bg-green-100 text-green-700',
      50: 'bg-amber-100 text-amber-700',
      10: 'bg-red-100 text-red-700',
    };
    return classes[role] ?? 'bg-slate-100 text-slate-600';
  }

  getInitials(name: string): string {
    return name
      .split(' ')
      .map((n) => n[0])
      .join('')
      .toUpperCase()
      .slice(0, 2);
  }
}
