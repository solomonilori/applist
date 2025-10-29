import { WebPlugin } from '@capacitor/core';

import type { ApplistPlugin, Apps } from './definitions';

export class ApplistWeb extends WebPlugin implements ApplistPlugin {
  async getInstalledApps(): Promise<Apps> {
    const emptyApps: Apps = {apps: []}; 
    return emptyApps;
  }
}
