import {
    React,
    renderToString,
    SheetsRegistry,
    JssProvider,
    MuiThemeProvider,
    createGenerateClassName,
    theme,
    Game,
    App,
} from './dist/server.bundle.js';
import {game, app} from './src/Html';

module.exports = function (server) {
    server.get('/game', (req, res) => {
        res.send(
            game(renderToString(<Game />))
        );
    });

    server.get('/app', (req, res) => {
        const sheetsRegistry = new SheetsRegistry();

        const html = renderToString(
          <JssProvider registry={sheetsRegistry} generateClassName={createGenerateClassName()}>
              <MuiThemeProvider theme={theme} sheetsManager={new Map()}>
                  <App />
              </MuiThemeProvider>
          </JssProvider>
        );

        res.send(
            app(html, sheetsRegistry.toString())
        );
    });
};
