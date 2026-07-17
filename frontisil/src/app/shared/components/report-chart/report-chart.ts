import { Component, input } from '@angular/core';

export interface ChartItem {
  label: string;
  value: number;
}

export type ChartType = 'pie' | 'bar';

@Component({
  selector: 'app-report-chart',
  templateUrl: './report-chart.html',
})
export class ReportChart {
  items = input.required<ChartItem[]>();
  chartType = input<ChartType>('pie');

  private readonly colors = [
    '#00aeef',
    '#0095cc',
    '#6366f1',
    '#a855f7',
    '#22c55e',
    '#f59e0b',
    '#ef4444',
    '#64748b',
  ];

  total(): number {
    return this.items().reduce((sum, item) => sum + item.value, 0);
  }

  color(index: number): string {
    return this.colors[index % this.colors.length];
  }

  percentage(value: number): number {
    const total = this.total();
    if (total === 0) return 0;
    return Math.round((value / total) * 100);
  }

  barWidth(value: number): number {
    const max = Math.max(...this.items().map((i) => i.value), 1);
    return (value / max) * 100;
  }

  pieSlices(): { path: string; color: string; label: string; value: number }[] {
    const total = this.total();
    if (total === 0) return [];

    const cx = 100;
    const cy = 100;
    const r = 80;
    let startAngle = -Math.PI / 2;

    return this.items().map((item, index) => {
      const sliceAngle = (item.value / total) * 2 * Math.PI;
      const endAngle = startAngle + sliceAngle;
      const path = this.describeArc(cx, cy, r, startAngle, endAngle);
      const slice = {
        path,
        color: this.color(index),
        label: item.label,
        value: item.value,
      };
      startAngle = endAngle;
      return slice;
    });
  }

  private describeArc(
    cx: number,
    cy: number,
    r: number,
    startAngle: number,
    endAngle: number
  ): string {
    if (this.items().length === 1) {
      return `M ${cx - r} ${cy} A ${r} ${r} 0 1 0 ${cx + r} ${cy} A ${r} ${r} 0 1 0 ${cx - r} ${cy} Z`;
    }

    const start = this.polarToCartesian(cx, cy, r, endAngle);
    const end = this.polarToCartesian(cx, cy, r, startAngle);
    const largeArc = endAngle - startAngle > Math.PI ? 1 : 0;

    return [
      `M ${cx} ${cy}`,
      `L ${start.x} ${start.y}`,
      `A ${r} ${r} 0 ${largeArc} 0 ${end.x} ${end.y}`,
      'Z',
    ].join(' ');
  }

  private polarToCartesian(cx: number, cy: number, r: number, angle: number) {
    return {
      x: cx + r * Math.cos(angle),
      y: cy + r * Math.sin(angle),
    };
  }
}
