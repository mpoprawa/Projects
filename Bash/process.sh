#!/bin/sh

a1=("PPID")
a2=("PID")
a3=("comm")
a4=("state")
a5=("tty")
a6=("rss")
a7=("pgid")
a8=("sid")
id=$(ps -Ao pid)
arrId=(${id// / })
unset 'arrId[${#arrId[@]}-1]';
for var in "${arrId[@]}"
do
	if [ "$var" != "PID" ] ; then
		process=$(cat /proc/$var/stat)
		#arrP=(${process//")"/})
		IFS=')' read -r -a process <<< "$process"
		first=${process[0]}
		IFS='(' read -r -a first <<< "$first"
		second=${process[1]}
		IFS=' ' read -r -a second <<< "$second"
		ppid=${second[1]}
		a1+=($ppid)
		pid=${first[0]}
		a2+=($pid)
		comm=${first[1]}
		a3+=($comm)
		state=${second[0]}
		a4+=($state)
		tty=${second[4]}
		a5+=($tty)
		rss=${second[21]}
		a6+=($rss)
		pgid=${second[2]}
		a7+=($pgid)
		sid=${second[3]}
		a8+=($sid)
	fi
done
for i in "${!a1[@]}"; do
    printf "%-7s %-7s %-37s %-7s %-7s %-7s %-7s %-7s\n" "${a1[i]}" "${a2[i]}" "${a3[i]}" "${a4[i]}" "${a5[i]}" "${a6[i]}" "${a7[i]}" "${a8[i]}"
done

        
        
