import { Component, input } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-page-layout',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './page-layout.html',
})
export class PageLayout {
  title = input.required<string>();
  subtitle = input<string>('');
}
