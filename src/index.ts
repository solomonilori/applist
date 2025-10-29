import { registerPlugin } from '@capacitor/core';

import type { ApplistPlugin } from './definitions';

const AppList = registerPlugin<ApplistPlugin>('Applist', {
  web: () => import('./web').then((m) => new m.ApplistWeb()),
});

export * from './definitions';
export { AppList };
