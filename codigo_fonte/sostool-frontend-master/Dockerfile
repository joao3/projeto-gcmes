FROM node:20

COPY package.json /app/
COPY ./ /app

WORKDIR /app

RUN npm install

ENTRYPOINT npm run start