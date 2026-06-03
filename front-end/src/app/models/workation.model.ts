/**
 * @author sowmya.lingaiah
 */
export type RiskLevel = 'NO' | 'LOW' | 'HIGH';

export interface Workation {
  workationId: string;
  employee: string;
  origin: string;
  destination: string;
  start: string;
  end: string;
  workingDays: number;
  risk: RiskLevel;
}

export type SortDirection = 'asc' | 'desc' | '';

export interface SortState {
  column: string;
  direction: SortDirection;
}
