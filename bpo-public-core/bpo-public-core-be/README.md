## Local Environment

Install [podman](https://podman.io/).

Run the following command in the directory, where `bpo-local-pod.yaml` file is. It will download and install all containers:

`podman play kube bpo-local-pod.yaml`

Note that the following ports should be available: 1025, 1080, 1433, 5432, 8978, 9000, 9001, 9200, 9300.  
If something goes wrong during installation the existing pod should be deleted, either from Podman desktop UI or with `podman pod rm bpo-registers` command.

This will start a pod named `bpo-registers` with the following containers:
- MSSQL server: <localhost:1433/ipas-db>
- Postgres DB: <localhost:5432>
- Cloudbeaver (<https://github.com/dbeaver/cloudbeaver>): <http://localhost:8978>
- Minio (<https://min.io/>): <http://localhost:9001>
- Elasticsearch: <http://localhost:9200>
- Mailcatcher (<https://mailcatcher.me/>): <http://localhost:1080>

Note that at the moment databases and minio buckets have to be manually created.

Run the applications with `-Dspring.profiles.active=local` profile (also run local bpo-config-server). 
