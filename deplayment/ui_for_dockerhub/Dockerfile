FROM nginx:1.17.1-alpine

EXPOSE 5000

COPY nginx.conf /etc/nginx/nginx.conf
COPY /dist /usr/share/nginx/html
COPY empty.png /usr/share/nginx/html/assets/img/logo_ed.png
COPY empty2.png /usr/share/nginx/html/assets/img/logo-H2020.png
COPY background.jpg /usr/share/nginx/html/assets/img/bg/dark-material-bg.jpg

RUN sed -i 's/, European Dynamics/, Onenet Application/g' /usr/share/nginx/html/main.*.js
