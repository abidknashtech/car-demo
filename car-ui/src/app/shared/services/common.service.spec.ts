import { TestBed } from '@angular/core/testing';
import { CommonService } from './common.service';
import { ColDef } from 'ag-grid-community';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('CommonService', () => {
  let service: CommonService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(CommonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return an empty array when no columns are selected', () => {
    const event: string[] = [];
    const tableHeaders: ColDef[] = [
      { colId: 'column1', headerName: 'Column 1' },
      { colId: 'column2', headerName: 'Column 2' },
      // Add more columns as needed
    ];

    const result = service.setTableHeaders(event, tableHeaders);

    expect(result).toEqual([]);
  });

  it('should return the selected columns', () => {
    const event: string[] = ['column1', 'column3'];
    const tableHeaders: ColDef[] = [
      { colId: 'column1', headerName: 'Column 1' },
      { colId: 'column2', headerName: 'Column 2' },
      { colId: 'column3', headerName: 'Column 3' },
      // Add more columns as needed
    ];

    const result = service.setTableHeaders(event, tableHeaders);

    expect(result).toEqual([
      { colId: 'column1', headerName: 'Column 1' },
      { colId: 'column3', headerName: 'Column 3' },
    ]);
  });

  it('should return an empty array when all columns are selected but event is empty', () => {
    const event: string[] = [];
    const tableHeaders: ColDef[] = [
      { colId: 'column1', headerName: 'Column 1' },
      { colId: 'column2', headerName: 'Column 2' },
      // Add more columns as needed
    ];

    const result = service.setTableHeaders(event, tableHeaders);

    expect(result).toEqual([]);
  });

  it('should handle case where event contains non-existent column IDs', () => {
    const event: string[] = ['column1', 'nonExistentColumn'];
    const tableHeaders: ColDef[] = [
      { colId: 'column1', headerName: 'Column 1' },
      { colId: 'column2', headerName: 'Column 2' },
      // Add more columns as needed
    ];

    const result = service.setTableHeaders(event, tableHeaders);

    expect(result).toEqual([
      { colId: 'column1', headerName: 'Column 1' },
    ]);
  });
});
