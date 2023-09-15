#Prometheus Deploy
kubectl create configmap prometheus-server-conf --from-file=./deploy/prometheus/k8s/config/prometheus.yml

kubectl apply -f ./deploy/prometheus/k8s/deployment.yaml
kubectl apply -f ./deploy/prometheus/k8s/service.yaml

printf "Prometheus Deployed!"

#RabbitMQ Deploy
kubectl apply -f ./deploy/rabbitmq/k8s/deployment.yaml
kubectl apply -f ./deploy/rabbitmq/k8s/service.yaml

#grafana Deploy
kubectl apply -f ./deploy/grafana/k8s/deployment.yaml
kubectl apply -f ./deploy/grafana/k8s/service.yaml

printf "Grafana Deployed!"

#DeployMS
for dir in $(find -type d); do
  if [ -f "$dir/deployMS.sh" ]; then
    cd "$dir"
    bash deployMS.sh
    cd -
  fi
done

printf 'Microservices Deployed! ;D'