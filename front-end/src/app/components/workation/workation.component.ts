/**
 * @author sowmya.lingaiah
 */
import { Component, OnInit } from '@angular/core';
import { Workation, SortState, RiskLevel } from '../../models/workation.model';
import { WorkationService } from '../../services/workation.service';

@Component({
  selector: 'app-workation',
  templateUrl: './workation.component.html',
  styleUrls: ['./workation.component.scss']
})
export class WorkationComponent implements OnInit {
  workations: Workation[] = [];
  loading = true;
  error = '';
  sortState: SortState = { column: 'workationId', direction: 'asc' };

  private readonly countryIsoCodes: Record<string, string> = {
    'Germany':       'de',
    'United States': 'us',
    'Ukraine':       'ua',
    'Belgium':       'be',
    'Spain':         'es',
    'Greece':        'gr',
    'India':         'in',
    'France':        'fr',
    'Italy':         'it',
    'Netherlands':   'nl',
    'Portugal':      'pt',
    'Poland':        'pl',
    'Austria':       'at',
    'Switzerland':   'ch',
    'Sweden':        'se',
    'Norway':        'no',
    'Denmark':       'dk',
    'Finland':       'fi',
    'Croatia':       'hr',
    'Hungary':       'hu',
  };

  constructor(private workationService: WorkationService) {}

  ngOnInit(): void {
    this.loadWorkations();
  }

  loadWorkations(): void {
    this.loading = true;
    this.error = '';
    this.workationService.getWorkations(this.sortState.column, this.sortState.direction || 'asc')
      .subscribe({
        next: (data) => {
          this.workations = data;
          this.loading = false;
        },
        error: (err) => {
          this.error = 'Failed to load workations. Please ensure the backend is running.';
          this.loading = false;
          console.error(err);
        }
      });
  }

  sort(column: string): void {
    if (this.sortState.column === column) {
      this.sortState.direction = this.sortState.direction === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortState = { column, direction: 'asc' };
    }
    this.loadWorkations();
  }

  getSortIcon(column: string): string {
    if (this.sortState.column !== column) return '↕';
    return this.sortState.direction === 'asc' ? '↑' : '↓';
  }

  getFlagUrl(country: string): string {
    const code = this.countryIsoCodes[country];
    if (!code) return '';
    return `https://flagcdn.com/20x15/${code}.png`;
  }

  getCountryCode(country: string): string {
    return (this.countryIsoCodes[country] || '??').toUpperCase();
  }

  getRiskLabel(risk: RiskLevel): string {
    switch (risk) {
      case 'NO':   return 'No risk';
      case 'LOW':  return 'No risk';
      case 'HIGH': return 'High risk';
      default:     return risk;
    }
  }

  getRiskClass(risk: RiskLevel): string {
    switch (risk) {
      case 'NO':   return 'risk-no';
      case 'LOW':  return 'risk-low';
      case 'HIGH': return 'risk-high';
      default:     return '';
    }
  }

  getRiskIcon(risk: RiskLevel): string {
    switch (risk) {
      case 'NO':   return 'assets/icons/green-risk.svg';
      case 'LOW':  return 'assets/icons/yellow-risk.svg';
      case 'HIGH': return 'assets/icons/red-risk.svg';
      default:     return '';
    }
  }
}
