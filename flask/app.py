from flask import Flask
from flask import request, jsonify, send_from_directory, abort
import os
import json
import random
import hashlib

app = Flask(__name__)

video_file_name = ['video1.mp4', 'video2.mp4']
pic_file_name = ['pic1.jpg', 'pic2.jpg', 'pic3.jpg', 'pic4.jpg']

xml = """
<apps>
<app>
<id>1</id>
<name>Google Maps</name>
<version>1.0</version>
</app>
<app>
<id>2</id>
<name>Chrome</name>
<version>2.1</version>
</app>
<app>
<id>3</id>
<name>Google Play</name>
<version>2.3</version>
</app>
</apps>"""

a_json = """
[{"id":"5","version":"5.5","name":"Angry Birds"},
{"id":"6","version":"7.0","name":"Clash of Clans"},
{"id":"7","version":"3.5","name":"Hey Day"}]"""


@app.route('/')
def hello_world():
    return 'Hello World'
    return xml


@app.route('/get_xml')
def get_xml():
    return xml


@app.route('/get_json')
def get_json():
    return a_json


@app.route('/pic')
def request_pic():

    return_dict = {}

    if request.method == "GET":

        index = random.randint(0, len(pic_file_name) - 1)

        file_md5 = ''
        path = os.getcwd()
        file_path = os.path.join(path, 'pic' + os.path.sep + pic_file_name[index])
        with open(file_path, 'rb') as f:
            file_md5 = get_file_md5(f)

        return_dict['type'] = 'pic'
        return_dict['name'] = pic_file_name[index]
        return_dict['md5'] = file_md5

        if os.path.isfile(os.path.join('pic', pic_file_name[index])):
            return json.dumps(return_dict)
        abort(404)


@app.route('/video')
def download_video():

    return_dict = {}

    index = random.randint(0, len(video_file_name) - 1)

    file_md5 = ''
    path = os.getcwd()
    file_path = os.path.join(path, 'video' + os.path.sep + video_file_name[index])
    with open(file_path, 'rb') as f:
        file_md5 = get_file_md5(f)

    return_dict['type'] = 'video'
    return_dict['name'] = video_file_name[index]
    return_dict['md5'] = file_md5

    if os.path.isfile(file_path):
        return json.dumps(return_dict)
    abort(404)


@app.route('/download/<path:url_path>/')
def download(url_path):
    if request.method == "GET":

        file_type = url_path.split('/')[0]
        name = url_path.split('/')[1]

        if os.path.isfile(os.path.join(file_type, name)):
            return send_from_directory(file_type, name, as_attachment=True)
        abort(404)


def get_file_md5(file):
    content = file.read()
    file_md5 = hashlib.md5(content).hexdigest()
    return file_md5


if __name__ == '__main__':
    app.run(host="0.0.0.0", port=8080)
