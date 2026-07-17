import { Component, inject, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../../core/services/user.service';
import {
  CountByDomain,
  CountByRole,
  ReportUser,
  UserReportResponse,
} from '../../../core/models/user-report.model';
import { PageLayout } from '../../../shared/components/page-layout/page-layout';
import { ChartItem, ChartType, ReportChart } from '../../../shared/components/report-chart/report-chart';

@Component({
  selector: 'app-user-report',
  imports: [PageLayout, FormsModule, ReportChart],
  templateUrl: './user-report.html',
})
export class UserReport implements OnInit {
  private userService = inject(UserService);

  loading = signal(true);
  error = signal<string | null>(null);
  countByDomain = signal<CountByDomain[]>([]);
  countByRole = signal<CountByRole[]>([]);
  users = signal<ReportUser[]>([]);
  page = signal(0);
  size = signal(10);
  totalElements = signal(0);
  totalPages = signal(0);

  emailType = signal('');
  roleFilter = signal('');
  chartType = signal<ChartType>('pie');

  ngOnInit(): void {
    this.loadReport();
  }

  loadReport(): void {
    this.loading.set(true);
    this.error.set(null);

    this.userService
      .getUserReport({
        emailType: this.emailType() || undefined,
        role: this.roleFilter() || undefined,
        page: this.page(),
        size: this.size(),
      })
      .subscribe({
        next: (report: UserReportResponse) => {
          this.countByDomain.set(report.countByDomain);
          this.countByRole.set(report.countByRole);
          this.users.set(report.users.content);
          this.page.set(report.users.page);
          this.size.set(report.users.size);
          this.totalElements.set(report.users.totalElements);
          this.totalPages.set(report.users.totalPages);
          this.loading.set(false);
        },
        error: () => {
          this.error.set('No se pudo cargar el reporte. Verifica que el servidor esté activo.');
          this.loading.set(false);
        },
      });
  }

  applyFilters(): void {
    this.page.set(0);
    this.loadReport();
  }

  clearFilters(): void {
    this.emailType.set('');
    this.roleFilter.set('');
    this.page.set(0);
    this.loadReport();
  }

  goToPage(newPage: number): void {
    if (newPage < 0 || newPage >= this.totalPages()) return;
    this.page.set(newPage);
    this.loadReport();
  }

  getInitials(name: string): string {
    return name
      .split(' ')
      .map((n) => n[0])
      .join('')
      .toUpperCase()
      .slice(0, 2);
  }

  getRoleBadgeClass(role: string): string {
    const classes: Record<string, string> = {
      admin: 'bg-purple-100 text-purple-700',
      user: 'bg-green-100 text-green-700',
    };
    return classes[role.toLowerCase()] ?? 'bg-slate-100 text-slate-600';
  }

  totalUsersByDomain(): number {
    return this.countByDomain().reduce((sum, item) => sum + item.count, 0);
  }

  totalUsersByRole(): number {
    return this.countByRole().reduce((sum, item) => sum + item.count, 0);
  }

  setChartType(type: ChartType): void {
    this.chartType.set(type);
  }

  domainChartData(): ChartItem[] {
    return this.countByDomain().map((item) => ({
      label: item.domain,
      value: item.count,
    }));
  }

  roleChartData(): ChartItem[] {
    return this.countByRole().map((item) => ({
      label: item.role,
      value: item.count,
    }));
  }
}
