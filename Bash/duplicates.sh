#!/bin/sh

echo > files.txt
path=$1
files=$(find -L $path -type f)
files=(${files// / })
for file in "${files[@]}"
do
	size=$(du -b $file | cut -f -1)
	code=$(sha256sum $file | cut -d " " -f 1)
	echo $file" "$size" "$code >> files.txt
done
awk 'NR==FNR{a[$3]++;next;}{ if (a[$3] > 1)print $2,$1,$3;}' files.txt files.txt | sort -rn
