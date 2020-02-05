kubectl delete -k ./kt
timeout /t  30
kubectl apply -k ./kt
timeout /t  30
kubectl get pods
timeout /t  30
cls
kubectl get pods
timeout /t 30
cls
kubectl get pods
timeout /t  30
cls
kubectl get pods
timeout /t  30
cls
kubectl get pods
timeout /t  10
kubectl get services
kubectl port-forward svc/frontend-lb 80:80