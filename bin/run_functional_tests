#!/usr/bin/env ruby

project_bin_dir = File.join(File.dirname(File.expand_path(__FILE__)))
functional_spec_dir = File.join(project_bin_dir, '..', 'functional_spec')
cmd = "cd #{functional_spec_dir}; bundle install; bundle exec rake spec:functional"
puts cmd
system cmd
