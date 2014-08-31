import os
from flask import Flask, render_template, request, jsonify
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


@app.route("/points/", methods=["GET", "POST"])
def points():
    if request.method == "POST":
        print request.get_json()
        return jsonify(status='ok', id='999')
    else:
        mock_points = [
            {'id': '1', 'lat': '47.85314102183853', 'lng': '35.175132751464844', 'title': 'point1'},
            {'id': '2', 'lat': '47.84542268497529', 'lng': '35.11676788330078', 'title': 'point2'},
            {'id': '3', 'lat': '47.80704435615207', 'lng': '35.167236328125', 'title': 'point3'}
        ]
        return jsonify(points=mock_points)


if __name__ == "__main__":
    app.run(debug=True)
