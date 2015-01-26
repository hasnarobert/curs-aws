ACCOUNT_NAMES="
student1
student2"

GROUP_NAME="sample-group"
DEFAULT_PASSWORD="default-password"

for ACCOUNT in $ACCOUNT_NAMES; do
    echo Creating account: $ACCOUNT
    aws iam create-user --user-name $ACCOUNT
    aws iam add-user-to-group --user-name "$ACCOUNT" --group-name "$GROUP_NAME"
    aws iam create-access-key --user-name "$ACCOUNT"
    aws iam create-login-profile --user-name "$ACCOUNT" --password-reset-required --password "$DEFAULT_PASSWORD"
    printf "\n\n\n"
done
