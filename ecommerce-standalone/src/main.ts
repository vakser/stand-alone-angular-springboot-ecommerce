import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import {provideAnimations} from "@angular/platform-browser/animations";
import {provideRouter} from "@angular/router";
import {routes} from "./app/app.routes";
import {provideHttpClient, withInterceptorsFromDi} from "@angular/common/http";
import {APP_INITIALIZER, importProvidersFrom} from "@angular/core";
import {PaginationModule} from "ngx-bootstrap/pagination";
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";

function initializeKeycloak(keycloak: KeycloakService) {
  return () => keycloak.init({
    config: {
      url: 'http://localhost:9003/',
      realm: 'oauth2-demo-realm',
      clientId: 'angular-ecommerce'
    },
    initOptions: {
      onLoad: 'check-sso',
      pkceMethod: 'S256',
      silentCheckSsoRedirectUri: window.location.origin + '/silent-verify-sso.html'
    },
    loadUserProfileAtStartUp: false
  })

}

bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(PaginationModule.forRoot(), KeycloakAngularModule),
    KeycloakService,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    },
    provideAnimations(),
    provideHttpClient(withInterceptorsFromDi()),
    provideRouter(routes)
  ]
})
  .catch((err) => console.error(err));
