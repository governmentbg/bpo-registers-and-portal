import React from "react";
import ReactDOM from "react-dom";
import { messagesBg, messagesEn } from "./i18n";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { BrowserRouter } from "react-router-dom";
import {
  AppI18nProvider,
  AppSecurityProvider,
  keycloakInitObject,
  ProcessEnvironments,
} from "@duosoftbg/bpo-components";
import { Provider } from "react-redux";
import { store } from "./store/redux/store";
import { GoogleReCaptchaProvider } from "react-google-recaptcha-v3";

ReactDOM.render(
  <React.StrictMode>
    <GoogleReCaptchaProvider reCaptchaKey={ProcessEnvironments.Recaptcha.Sitekey}>
      <BrowserRouter basename={ProcessEnvironments.PublicUrl}>
        <AppSecurityProvider
          authClient={keycloakInitObject}
          // initOptions={{ onLoad: "login-required", pkceMethod: "S256", checkLoginIframe: false }}
        >
          <Provider store={store}>
            <AppI18nProvider messagesBg={messagesBg} messagesEn={messagesEn}>
              <App />
            </AppI18nProvider>
          </Provider>
        </AppSecurityProvider>
      </BrowserRouter>
    </GoogleReCaptchaProvider>
  </React.StrictMode>,
  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
