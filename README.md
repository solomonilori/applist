# app-list

The plugin is used for listing installed apps

## Install

```bash
npm install app-list
npx cap sync
```

## API

<docgen-index>

* [`getInstalledApps()`](#getinstalledapps)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getInstalledApps()

```typescript
getInstalledApps() => Promise<Apps>
```

**Returns:** <code>Promise&lt;<a href="#apps">Apps</a>&gt;</code>

--------------------


### Interfaces


#### Apps

| Prop       | Type               |
| ---------- | ------------------ |
| **`apps`** | <code>App[]</code> |


#### App

| Prop           | Type                |
| -------------- | ------------------- |
| **`appId`**    | <code>string</code> |
| **`appName`**  | <code>string</code> |
| **`iconUrl`**  | <code>string</code> |
| **`category`** | <code>string</code> |

</docgen-api>
