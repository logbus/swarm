FROM node:13.7.0-alpine AS builder
RUN apk add g++ make python
WORKDIR frontend

COPY package*.json ./
RUN npm ci

COPY . .
RUN ls -al ./src/app
RUN npm run build
RUN ls -al
RUN ls -al dist

FROM nginx:1.16.1
RUN rm -Rf /etc/nginx/html
COPY --from=builder frontend/dist/frontend/ /etc/nginx/html/
COPY nginx.conf etc/nginx/nginx.conf
RUN ls -al etc/nginx/
RUN ls -al /etc/nginx/html/
