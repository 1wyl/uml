#!/bin/bash
echo "Enter commit message:"
read commit_message
git add .
git commit -m "$commit_message"
git push

echo "按任意键继续..."
read -r -n 1 -s  # 等待用户按下任意键