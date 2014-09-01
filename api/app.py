import os
from flask import Flask, render_template, request, jsonify
from flask.ext import assets

from models import Point

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
        'handlebars/handlebars.min.js',
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
        data = request.get_json()
        point = Point(**data)
        point.save()
        return jsonify(status='ok', id=point.id)
    else:
        return jsonify(
            points=[p.to_dict() for p in Point.query.order_by('id').all()])


if __name__ == "__main__":
    app.run(debug=True)
