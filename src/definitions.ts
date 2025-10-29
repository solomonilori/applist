export interface ApplistPlugin {
  getInstalledApps(): Promise<Apps>;
}

export interface Apps{
  apps: App[];
}
export interface App{
  appId: string;
  appName: string;
  iconUrl: string;
  category: string;
}

