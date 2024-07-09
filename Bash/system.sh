#!bin/sh

while true
do
	net1=$(head -n 3 /proc/net/dev | tail -1)
	IFS=$' ' read -rd '' -a net1 <<< "$net1"
	net2=$(head -n 4 /proc/net/dev | tail -1)
	IFS=$' ' read -rd '' -a net2 <<< "$net2"
	received=$((${net1[1]}+${net2[1]}))
	transmited=$((${net1[9]}+${net2[9]}))
	echo "Bytes received: "$received
	echo "Bytes transmited: "$transmited
	freq=$(cat /proc/cpuinfo | grep Hz)
	IFS=$'\n' read -rd '' -a freq <<< "$freq"
	for i in {1..8}
	do
		proc=$(head -n $(($i+1)) /proc/stat | tail -1)
		proc=(${proc// / })
		idle=$((${proc[4]}+${proc[5]}))
		busy=$((${proc[1]}+${proc[2]}+${proc[3]}+${proc[6]}+${proc[7]}+${proc[8]}))
		perc=$(((100*$idle)/($busy+$idle)))
		echo ${proc[0]}" usage: ""${perc:0:-2}.${perc: -2}""% frequency: "${freq[$(($i-1))]:11:8}
	done
	time=$(cat /proc/uptime)
	uptime=${time%%.*}
	days=$(($uptime/86400))
	uptime=$(($uptime % 86400))
	h=$(($uptime/3600))
	uptime=$(($uptime % 3600))
	min=$(($uptime/60))
	uptime=$(($uptime % 60))
	echo "Running for: "$days" days "$h" hours "$min" minutes "$uptime" seconds"
	battery=$(sudo cat /sys/class/power_supply/BAT0/uevent)
	battery="${battery#*CAPACITY=}"
	battery=${battery:0:2}
	echo "Battery capacity: "${battery}
	load=$(cat /proc/loadavg)
	echo "System load: "$load
	head -n 3 /proc/meminfo 
	echo ""
	sleep 1
done
