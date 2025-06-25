// render-markmap.js
const fs = require("fs");
const { Transformer } = require("markmap-lib");

const inputPath = process.argv[2];
const outputPath = process.argv[3];

// Leer markdown
const markdown = fs.readFileSync(inputPath, "utf8");

// Transformar a estructura de nodos
const transformer = new Transformer();
const { root } = transformer.transform(markdown);

// HTML con Markmap funcionando
const html = `
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>Mapa conceptual - Maen Studios</title>
    <style>
      html, body, svg { width: 100%; height: 100%; margin: 0; padding: 0; }
    </style>
  </head>
  <body>
    <svg id="markmap" />
    <script src="https://cdn.jsdelivr.net/npm/d3@7"></script>
    <script src="https://cdn.jsdelivr.net/npm/markmap-view"></script>
    <script>
      const { Markmap } = window.markmap;
      Markmap.create("#markmap", null, ${JSON.stringify(root)});
    </script>
  </body>
</html>
`;

fs.writeFileSync(outputPath, html);

