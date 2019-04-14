# Beleza na WEB

## Install docker

```shell
sudo apt-get install apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"
sudo apt update
sudo apt install docker-ce
```

#### View status docker:
`sudo systemctl status docker`

#### Run without sudo command:
```shell
sudo usermod -aG docker ${USER}
su - ${USER}
sudo usermod -aG docker user
```
## Install Docker-Compose
```shell
sudo su -
curl -L https://github.com/docker/compose/releases/download/1.8.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
```
#### Execute application Java Spring-Boot with MongoDB
`sudo docker-compose -f docker-compose.yml up --build`

#### Run navigator Swagger:
http://localhost:8666/swagger-ui.html