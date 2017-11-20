
from flask import Flask, make_response
import os
import socket
import getpass

app = Flask(__name__)

@app.route("/")
def hello():

    html = "<h3>Hello!</h3>" \
           "<b>Hostname:</b> {hostname}<br/>"

    f_list = os.listdir('.')
    f_list = sorted(f_list)
    for f in f_list:
        splited = os.path.splitext(f)
        if splited[1] == '.apk':
            item = "<a href=\"apk\%s\">%s</a><br/>"%(f, splited[0])
            html = "%s\n%s"%(html, item)

    reminder = "<b>Please select one apk file to download"
    html = "%s\n%s"%(html, reminder)

    return html.format(hostname=socket.gethostname())

@app.route("/apk/<filename>")
def apk(filename):
    resp = make_response()
    resp.status_code = 200
    resp.headers['Content-Type'] = 'application/vnd.android.package-archive'
    resp.headers['Content-Disposition'] = 'attachment;filename='+filename

    apk_file = open(filename, "r")
    resp.data = apk_file.read()

    return resp

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=80)
