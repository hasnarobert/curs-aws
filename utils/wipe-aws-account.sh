AWS_ACCOUNT_ID=

AWS_REGIONS=`aws ec2 describe-regions | jq -r ".Regions[].RegionName" | tr '\n' ' '`
AWS_REGIONS=${AWS_REGIONS%?}

for AWS_REGION in ${AWS_REGIONS}; do
    echo "Deleting all resources for region ${AWS_REGION}"
    export AWS_DEFAULT_REGION=${AWS_REGION}

    ########################################
    # Delete all EC2 and VPC resources
    ########################################

    # Instances
    INSTANCE_IDS=`aws ec2 describe-instances --filters Name=instance-state-code,Values=0,16,32,64,80 | jq -r ".Reservations[].Instances[].InstanceId" | tr '\n' ' '`
    INSTANCE_IDS=${INSTANCE_IDS%?}
    echo "Terminating instances: ${INSTANCE_IDS}"
    [[ ${INSTANCE_IDS} ]] && aws ec2 terminate-instances --instance-ids ${INSTANCE_IDS}

    # AMIs
    aws ec2 describe-images --filters Name=owner-id,Values=${AWS_ACCOUNT_ID} | jq -r ".Images[].ImageId" | while read AMI_ID; do
        echo "Deregistering AMI: ${AMI_ID}"
        aws ec2 deregister-image --image-id ${AMI_ID}
    done

    # EBS Volumes

    # Snapshots
    aws ec2 describe-snapshots --filters Name=owner-id,Values=${AWS_ACCOUNT_ID} | jq -r ".Snapshots[].SnapshotId" | while read SNAPSHOT_ID; do
        echo "Deleting snapshot: ${SNAPSHOT_ID}"
        aws ec2 delete-snapshot --snapshot-id ${SNAPSHOT_ID}
    done

    # Map Reduce Clusters
    aws emr list-clusters | jq -r ".Clusters[].Id" | while read CLUSTER_ID; do
        echo "Deleting EMR cluster: ${CLUSTER_ID}"
        aws emr terminate-clusters --cluster-ids ${CLUSTER_ID}
    done

    # SecurityGroups
    aws ec2 describe-security-groups | jq -r ".SecurityGroups[].GroupId" | while read SG_ID; do
        echo "Deleting security group: ${SG_ID}"
        aws ec2 delete-security-group --group-id ${SG_ID}
    done

    # RDS instances
    aws rds describe-db-instances | jq -r ".DBInstances[].DBInstanceIdentifier" | while read DB_INSTANCE_ID; do
        echo "Deleting RDS instance: ${DB_INSTANCE_ID}"
        aws rds delete-db-instance --db-instance-identifier ${DB_INSTANCE_ID} --skip-final-snapshot
    done

    # DynamoDB
    aws dynamodb list-tables | jq -r ".TableNames[]" | while read TABLE_ID; do
        echo "Deleting dynamo tables: ${TABLE_ID}"
        aws dynamodb delete-table --table-name ${TABLE_ID}
    done

    # RDS snapshots
    aws rds describe-db-snapshots | jq -r ".DBSnapshots[].DBSnapshotIdentifier" | while read DB_SNAPSHOT_ID; do
        echo "Deleting RDS snapshot: ${DB_INSTANCE_ID}"
        aws rds delete-db-snapshot --db-snapshot-identifier ${DB_SNAPSHOT_ID}
    done

    # Route tables
    aws ec2 describe-route-tables | jq -r ".RouteTables[].RouteTableId" | while read ROUTE_TABLE_ID; do
        echo "Deleting route table: ${ROUTE_TABLE_ID}"
        aws ec2 delete-route-table --route-table-id ${ROUTE_TABLE_ID}
    done


    # Subnets
    aws ec2 describe-subnets | jq -r ".Subnets[].SubnetId" | while read SUBNET_ID; do
        echo "Deleting subnet: ${SUBNET_ID}"
        aws ec2 delete-subnet --subnet-id ${SUBNET_ID}
    done


    echo "DONE deleting resources for region ${AWS_REGION}"
done

