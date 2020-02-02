docker-machine create --driver hyperv --hyperv-virtual-switch "swarm" --hyperv-memory 1024 --hyperv-cpu-count 1 --hyperv-disk-size 10000 manager
docker-machine create --driver hyperv --hyperv-virtual-switch "swarm" --hyperv-memory 1024 --hyperv-cpu-count 1 --hyperv-disk-size 10000 node01
docker-machine create --driver hyperv --hyperv-virtual-switch "swarm" --hyperv-memory 1024 --hyperv-cpu-count 1 --hyperv-disk-size 10000 node02
docker-machine create --driver hyperv --hyperv-virtual-switch "swarm" --hyperv-memory 1024 --hyperv-cpu-count 1 --hyperv-disk-size 10000 node03