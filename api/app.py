import os
from flask import Flask, render_template
from flask.ext import assets


app = Flask(__name__)

env = assets.Environment(app)

env.load_path = [
    os.path.join(os.path.dirname(__file__), 'sass'),
    os.path.join(os.path.dirname(__file__), 'coffee'),
    os.path.join(os.path.dirname(__file__), 'bower_components'),
]

env.register(
    'app_js',
    assets.Bundle(
        'jquery/dist/jquery.min.js',
        'bootstrap/dist/js/bootstrap.min.js',
        assets.Bundle(
            'app.coffee',
            filters=['coffeescript']
        ),
        output='app.js'
    )
)

env.register(
    'app_style',
    assets.Bundle(
        'bootstrap/dist/css/bootstrap.min.css',
        assets.Bundle(
            'app.sass',
            filters='sass',
        ),
        output='style/app.css'
    )
)


@app.route("/")
def index():
    return render_template("index.html")

if __name__ == "__main__":
    app.run(debug=True)
