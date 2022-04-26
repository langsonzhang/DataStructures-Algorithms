while read -n1 p; do
    if [ $p = '[' -o $p = ] -o $p = , ]; then
        echo -n " "
    else
        echo -n $p
    fi
done < $1
echo 