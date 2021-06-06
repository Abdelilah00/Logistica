import {BaseModel} from './base-model.model';

export class Structure extends BaseModel {
  name: string;
  range: number;
}

export class StructureUnit extends BaseModel {
  name: string;
  structureName: string;
  actorId: number;
  actorName: string;
}
